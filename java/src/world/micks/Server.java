package world.micks;

import java.awt.Color;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNSFWEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.emote.EmoteRemovedEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


/**
 * JDA/Java remake of https://github.com/CTK-WARRIOR/Discord-Mod-Logs
 * @author Michael Faton | @idevmc 
 */

public class Server extends ListenerAdapter {
	
	static final long   modChannel = 1l;
	static final String token      = "";

	private static JDA jda;
	public static JDA getJda() { return jda; }
	
	public static void main(String[] args) throws LoginException {
		
		jda = JDABuilder.createDefault(token)
				
				.setActivity(Activity.watching("you."))
				.addEventListeners(new Server())
				
				.build();
		
	}
	
	@Override public void onReady(ReadyEvent event) { System.out.println("System has just started!!"); }
	
	@Override public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		if(event.getMessage().getContentRaw().equals("^ping")) {
			
			
			
		} else if(event.getMessage().getContentRaw().equals("^clean")) {
			
			event.getChannel().getHistory().retrievePast(5).queue(messages -> {
				event.getChannel().purgeMessages(messages);
			});
		
		}
		
	}
	
	@Override public void onTextChannelUpdateSlowmode(TextChannelUpdateSlowmodeEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Channel renamed")
				.setDescription("The text channel " + event.getChannel().getName() + "'s slowmode went from " + event.getOldSlowmode() + " to " + event.getNewSlowmode())
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	
	@Override public void onTextChannelCreate(TextChannelCreateEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Channel Created")
				.setDescription("A new text channel named " + event.getChannel().getName() + " has been created")
				.setColor(Color.green)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onTextChannelUpdateNSFW(TextChannelUpdateNSFWEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Channel NSFW status")
				.setDescription("The text channel " + event.getChannel().getName() + "'s NSFW status went from "+ event.getOldNSFW() + " to " + event.getNewValue())
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onTextChannelUpdateName(TextChannelUpdateNameEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Channel renamed")
				.setDescription("The text channel " + event.getOldName() + " has been renamed to " + event.getNewName())
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onTextChannelDelete(TextChannelDeleteEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Channel deleted")
				.setDescription("The text channel " + event.getChannel().getName() + " has been removed.")
				.setColor(Color.red)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Role added")
				.setDescription("The member " + event.getMember().getEffectiveName() + " has received the follow roles: " + String.join(" ,", event.getRoles().toArray().toString()))
				.setColor(Color.red)
				
				.build();
		
		channel.sendMessage(embed).queue();
	}
	
	@Override public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Role added")
				.setDescription("The member " + event.getMember().getEffectiveName() + " has received the follow roles: " + String.join(" ,", event.getRoles().toArray().toString()))
				.setColor(Color.red)
				
				.build();
		
		channel.sendMessage(embed).queue();
	}
	
	@Override public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Member removed")
				.setDescription("The member " + event.getMember().getEffectiveName() + " was either left, got kicked or got banned from the guild! ")
				.setColor(Color.red)
				
				.build();
		
		channel.sendMessage(embed).queue();
	}
	
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Member joined")
				.setDescription("The member " + event.getMember().getEffectiveName() + " has joined the guild!")
				.setColor(Color.green)
				
				.build();
		
		channel.sendMessage(embed).queue();
	}
	
	@Override public void onGuildUnban(GuildUnbanEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Role added")
				.setDescription("The member " + event.getUser().getAsTag() + " got their ban revoked.")
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
	}
	
	@Override public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Role added")
				.setDescription("The member " + event.getMember().getUser().getAsTag() + " changed their nickname from" + event.getOldNickname() == null ? event.getMember().getUser().getName() : event.getOldNickname() + " to " + event.getNewNickname() == null ? "" : event.getMember().getEffectiveName())
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
		
	}
	
	@Override public void onEmoteAdded(EmoteAddedEvent event) {
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Emote update")
				.setDescription("A new emote named " + event.getEmote().getName() + " was added to the guild!")
				.setColor(Color.green)
				
				.build();
		
		channel.sendMessage(embed).queue();
	}
	
	@Override public void onEmoteRemoved(EmoteRemovedEvent event) {
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Emote update")
				.setDescription("The emote " + event.getEmote().getName() + " was removed..")
				.setColor(Color.red)
				
				.build();
		
		channel.sendMessage(embed).queue();
	}
	
	@Override public void onEmoteUpdateName(EmoteUpdateNameEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Emote update")
				.setDescription("The emote " + event.getOldName() + " was changed to " + event.getNewName())
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onGuildUpdateName(GuildUpdateNameEvent event) {
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("GUild update")
				.setDescription("The guild's name has changed from " + event.getOldName() + " to " + event.getNewName())
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
	}
	
	@Override public void onGuildMessageDelete(GuildMessageDeleteEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Role added")
				.setDescription("A message has been deleted in " + event.getChannel().getAsMention())
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		Message message = event.getChannel().getHistory().getMessageById(event.getMessageId());
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Reaction added")
				.setDescription("The member " + event.getUser() == null ? "Entity" : event.getUser().getAsTag() + " reacted this [message](" + message.getJumpUrl() + ")")
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		Message message = event.getChannel().getHistory().getMessageById(event.getMessageId());
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Reaction added")
				.setDescription("The member " + event.getUser() == null ? "Entity" : event.getUser().getAsTag() + " reacted this [message](" + message.getJumpUrl() + ")")
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onGuildMessageReactionRemoveAll(GuildMessageReactionRemoveAllEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		Message message = event.getChannel().getHistory().getMessageById(event.getMessageId());
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Reaction pruned")
				.setDescription("All the reaction to this [message](" + message.getJumpUrl() + ") has been removed")
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		Message message = event.getChannel().getHistory().getMessageById(event.getMessageId());
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Message edited")
				.setDescription("Member " + event.getAuthor().getAsTag() + " edited this [message](" + message.getJumpUrl() + ")")
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override
	public void onRoleCreate(RoleCreateEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Role created")
				.setDescription("A new role has been created with the name " + event.getRole().getName())
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}

	@Override public void onRoleDelete(RoleDeleteEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Role removed")
				.setDescription("The role with the name" + event.getRole().getName() + " has been deleted")
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
	@Override public void onRoleUpdateName(RoleUpdateNameEvent event) {
		
		TextChannel channel = getJda().getTextChannelById(modChannel);
		
		MessageEmbed embed = new EmbedBuilder()
				
				.setAuthor("Role updated")
				.setDescription("The role with the old name " + event.getOldName() + " has been changed to " + event.getNewName())
				.setColor(Color.yellow)
				
				.build();
		
		channel.sendMessage(embed).queue();
		
	}
	
}
